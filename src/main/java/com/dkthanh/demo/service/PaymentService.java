package com.dkthanh.demo.service;

import com.dkthanh.demo.domain.Package;
import com.dkthanh.demo.domain.ProjectEntity;
import com.dkthanh.demo.domain.UserEntity;
import com.dkthanh.demo.domain.dto.PackageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PackageService packageService;

    @Autowired
    private StripeService stripeService;

    public static final Integer CENT_TRANSFER = 100;

    public String createCharge(String email, String token, String optionId, String projectId, Integer pledge){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        UserEntity user = null;

        // in real run, uncomment this block
        if(authentication != null) {

            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        if(!"".equals(username)){
            user = userService.findUserByUsername(username);
        }

        Integer userId  = user.getId();

        // update info of project
        ProjectEntity projectEntity = projectService.getProjectEntityById(Integer.parseInt(projectId));
        projectEntity.setPledged(projectEntity.getPledged() != null ? projectEntity.getPledged() + pledge : pledge);

        int count = 0;
        List<Package> listExistPack = packageService.getAllPackageByProjectId(Integer.parseInt(projectId));
        for(int i = 0; i< listExistPack.size(); i++){
            if(username.equals(listExistPack.get(i).getUser().getUsername()) ){
                count++;
            }
        }

        if(count == 0){
            projectEntity.setInvestorCount(projectEntity.getInvestorCount() != null ? projectEntity.getInvestorCount() + 1 : 0 + 1);
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PackageDto dto = new PackageDto(userId, Integer.parseInt(projectId), Integer.parseInt(optionId), new Long(pledge),timestamp);

        int rowCount = packageService.customSavePackage(dto);
        System.out.println("ROWCOUNT..........." + rowCount);

        String chargeId = stripeService.createCharge(email, token, pledge * CENT_TRANSFER);
        return chargeId;
    }

}
