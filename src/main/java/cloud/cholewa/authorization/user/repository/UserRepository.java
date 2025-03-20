package cloud.cholewa.authorization.user.repository;

import cloud.cholewa.authorization.user.model.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends R2dbcRepository<User, Long> {
}
