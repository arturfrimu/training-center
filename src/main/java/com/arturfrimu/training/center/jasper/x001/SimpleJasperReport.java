package com.arturfrimu.training.center.jasper.x001;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/jasper/simple-report")
@RequiredArgsConstructor
public class SimpleJasperReport {

    public static final List<Order> orders = new ArrayList<>();
    private final ReportService reportService;

    @GetMapping
    public List<Order> getOrders() {
        return orders;
    }

    @GetMapping("/generate/{reportFormat}")
    public String generateReport(@PathVariable String reportFormat) {
        return reportService.exportReport(reportFormat, orders);
    }

    static {
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order());
    }

    @Data
    public static class Order {
        private final UUID ID = UUID.randomUUID();
        private final String NAME = UUID.randomUUID().toString().substring(0, 5);
        private final String CATEGORY = UUID.randomUUID().toString().substring(0, 5);
        private final String STATUS = UUID.randomUUID().toString().substring(0, 5);
        private final int COUNT = new Random().nextInt(1, 6);
    }

    @Service
    public static class ReportService {
        public String exportReport(String reportFormat, final List<Order> data) {
            try {
                File file = ResourceUtils.getFile("classpath:order.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("createdBy", "Artur Frimu");
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

                if (reportFormat.equalsIgnoreCase("html")) {
                    JasperExportManager.exportReportToHtmlFile(jasperPrint, "/Users/arturfrimu/Desktop/jasper/orders.html");
                }
                if (reportFormat.equalsIgnoreCase("pdf")) {
                    JasperExportManager.exportReportToPdfFile(jasperPrint, "/Users/arturfrimu/Desktop/jasper/orders.pdf");
                }
            } catch (Exception e) {
                return "Error generating report";
            }

            return "Report generated in path /Users/arturfrimu/Desktop/jasper";
        }
    }
}
