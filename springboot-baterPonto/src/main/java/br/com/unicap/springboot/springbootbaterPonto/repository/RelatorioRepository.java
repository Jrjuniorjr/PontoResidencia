/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long>{
	
	@Procedure(name = "VALIDAR_PONTO_ENTRADA")
    public void VALIDAR_PONTO_ENTRADA(@Param("matricula") String matricula);
	
	@Procedure(name = "VALIDAR_PONTO_SAIDA")
    public void VALIDAR_PONTO_SAIDA(@Param("matricula") String matricula);

	/*@Procedure(name = "ID_BY_MATRICULA")
    public Long ID_BY_MATRICULA(@Param("matricula") String matricula);*/
}