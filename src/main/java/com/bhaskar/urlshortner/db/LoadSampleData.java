package com.bhaskar.urlshortner.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LoadSampleData implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void run(String... args) throws Exception {
        // Read SQL script from file
        ClassPathResource resource = new ClassPathResource("sample_data.sql");
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        String sqlScript = FileCopyUtils.copyToString(reader);

        // Execute SQL script
        jdbcTemplate.execute(sqlScript);

    }
}
