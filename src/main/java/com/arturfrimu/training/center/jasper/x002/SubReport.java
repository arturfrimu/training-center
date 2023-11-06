package com.arturfrimu.training.center.jasper.x002;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SubReport {

    public String exportReport(String reportFormat, final List<Item> data) {
        String fileName = "orders-with-sub-report";
        String destFileName = "/Users/arturfrimu/Desktop/jasper/%s.pdf".formatted(fileName);

        try {
            File subreportFile = ResourceUtils.getFile("classpath:jasper/subreport/sub-report-order.jrxml");
            JasperReport jasperSubReport = JasperCompileManager.compileReport(subreportFile.getAbsolutePath());

            File mainReportFile = ResourceUtils.getFile("classpath:jasper/subreport/main_report.jrxml");
            JasperReport jasperMainReport = JasperCompileManager.compileReport(mainReportFile.getAbsolutePath());

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Artur Frimu");
            parameters.put("ItemCollection", dataSource);
            parameters.put("SubreportParameter", jasperSubReport);  // asigurați-vă că acest parametru este adăugat la parametrii raportului principal

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperMainReport, parameters, dataSource);

            if ("pdf".equalsIgnoreCase(reportFormat)) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating report";
        }

        openFile(destFileName);

        return "Report generated in path /Users/arturfrimu/Desktop/jasper";
    }

    private static void openFile(final String pathname) {
        File pdfFile = new File(pathname);
        if (pdfFile.exists()) {

            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop desktop = Desktop.getDesktop();

                    desktop.open(pdfFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                log.info("Desktop is not supported");
            }
        } else {
            log.info("The file does not exist.");
        }
    }

    @Data
    @AllArgsConstructor
    public static class Item {
        private String name;
        private Double price;
    }
}
