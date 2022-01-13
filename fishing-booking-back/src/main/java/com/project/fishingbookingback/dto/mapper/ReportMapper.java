package com.project.fishingbookingback.dto.mapper;


import com.project.fishingbookingback.dto.request.ReportRequestDTO;
import com.project.fishingbookingback.model.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public Report toModel(ReportRequestDTO reportRequestDTO) {
        return new Report(reportRequestDTO.getComment(), reportRequestDTO.isSanction(), reportRequestDTO.isAppeared());
    }

}
