package com.senac.alunoB.component;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="viacep", url="172.20.208.1")
public class UsuarioFeignClient {
}
