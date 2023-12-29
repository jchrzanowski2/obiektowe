package pl.edu.agh.to2.example.dao;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to2.example.model.Roles;

@Repository
public interface RolesDAO extends R2dbcRepository<Roles, Long> {
}