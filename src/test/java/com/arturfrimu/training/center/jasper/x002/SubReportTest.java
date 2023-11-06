package com.arturfrimu.training.center.jasper.x002;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SubReportTest {

    private final SubReport subReport = new SubReport();

    @Test
    void exportReport() {
        String result = subReport.exportReport("pdf", List.of(
                new SubReport.Item("item 1", 1.0),
                new SubReport.Item("item 2", 2.0),
                new SubReport.Item("item 3", 3.0),
                new SubReport.Item("item 4", 4.0),
                new SubReport.Item("item 5", 5.0),
                new SubReport.Item("item 6", 6.0),
                new SubReport.Item("item 7", 7.0)
        ));

        assertThat(result).isEqualTo("Report generated in path /Users/arturfrimu/Desktop/jasper");
    }
}