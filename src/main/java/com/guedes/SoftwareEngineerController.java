package com.guedes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    @GetMapping
    public List<SoftwareEngineer> getEngineers() {
        return List.of(
                new SoftwareEngineer(
                        1,
                        "João Vitor Guedes",
                        "nextjs, typescript, node, prisma, tailwindcss, docker, db2, java, spring boot"
                ),
                new SoftwareEngineer(
                        2,
                        "Mario Cesar",
                        "analista SOC Jr, exploit, sql injection, deface, bypass redirect"
                        )
        );
    }

}
