package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.TeamRepository;
import com.dkthanh.demo.domain.TeamEntity;
import com.dkthanh.demo.domain.UserDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;


    public TeamEntity getTeamDetail(Integer teamId){
        Optional<TeamEntity> team = teamRepository.findById(teamId);
        if(team.isPresent()){
            return team.get();
        }
        return null;
    }
    // get participants of team
    public List<UserDetailEntity> getListParticipants(Integer teamId){
        List<UserDetailEntity> listUser = new ArrayList<>();

        return null;
    }
}
