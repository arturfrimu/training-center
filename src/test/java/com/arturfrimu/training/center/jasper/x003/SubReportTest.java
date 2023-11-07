package com.arturfrimu.training.center.jasper.x003;

import com.arturfrimu.training.center.jasper.x003.SubReport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SubReportTest {

    private final SubReport subReport = new SubReport();

    @Test
    void exportReport() {
        String result = subReport.exportReport("pdf", List.of(
                new SubReport.Item("item 1", 1.0),
                new SubReport.Item("item 2", 2.0),
                new SubReport.Item("item 3", 3.0),
                new SubReport.Item("item 4", 4.0)
        ), "jasper/subreport-work/sub-report-declaration.jrxml", "jasper/subreport-work/main-report-declaration.jrxml");

        assertThat(result).isEqualTo("Report generated in path /Users/arturfrimu/Desktop/jasper");
    }
}