package com.caretracking.ctapi.domain.reposiroty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caretracking.ctapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>
			, RestauranteRepositoryQueries/*consultas customizadas*/, JpaSpecificationExecutor<Restaurante>/*para uso genérico dos predicates*/ {
	
	//spdjpa-query-keywords: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	
	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
	
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
	
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long cozinha);
	
	@Query("from Restaurante where nome like %:nome% and cozinha.id= :id" ) //JPQL
	List<Restaurante> consultarPorNome(String nome, @Param("id")Long cozinhaId);
	//ou
	List<Restaurante> consultarPorNomeNamedQuery(String nome, @Param("id")Long cozinhaId);//JPQL de um arquivo externo /META-INF/orm.xml



}
