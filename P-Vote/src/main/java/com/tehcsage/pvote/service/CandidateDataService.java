package com.tehcsage.pvote.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.tehcsage.pvote.model.Candidate;
import com.tehcsage.pvote.model.CandidateData;

@Service
public class CandidateDataService {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CandidateData> getDataFromTables() {
    	List<CandidateData> result = new ArrayList<>();

        // Chairperson
        List<Candidate> chairpersonCandidates = jdbcTemplate.query("SELECT cand_name FROM chair_votes",
            (rs, rowNum) -> new Candidate(rs.getString("cand_name"), "Chairperson"));
        result.add(new CandidateData("Chairperson", chairpersonCandidates));

        // Secretary
        List<Candidate> secretaryCandidates = jdbcTemplate.query("SELECT cand_name FROM sec_votes",
            (rs, rowNum) -> new Candidate(rs.getString("cand_name"), "Secretary"));
        result.add(new CandidateData("Secretary", secretaryCandidates));

        // Treasurer
        List<Candidate> treasurerCandidates = jdbcTemplate.query("SELECT cand_name FROM treas_votes",
            (rs, rowNum) -> new Candidate(rs.getString("cand_name"), "Treasurer"));
        result.add(new CandidateData("Treasurer", treasurerCandidates));

        // Vice Chair
        List<Candidate> whipCandidates = jdbcTemplate.query("SELECT cand_name FROM vice_votes",
            (rs, rowNum) -> new Candidate(rs.getString("cand_name"), "Vice Chair"));
        result.add(new CandidateData("Vice Chair", whipCandidates));

        return result;
//        Map<String, List<String>> result = new HashMap<>();
//
//        result.put("Chairperson", jdbcTemplate.queryForList("SELECT cand_name FROM chair_votes", String.class));
//        result.put("Vice Chair", jdbcTemplate.queryForList("SELECT cand_name FROM vice_votes", String.class));
//        result.put("Secretary", jdbcTemplate.queryForList("SELECT cand_name FROM sec_votes", String.class));
//        result.put("Treasurer", jdbcTemplate.queryForList("SELECT cand_name FROM treas_votes", String.class));
//
//        return result;
    }
}
