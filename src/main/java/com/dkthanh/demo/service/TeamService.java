package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.TeamRepository;
import com.dkthanh.demo.domain.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
