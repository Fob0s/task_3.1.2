package ru.toropkin.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.toropkin.crud.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
