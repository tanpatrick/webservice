package patricktan.assignment.webservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import patricktan.assignment.webservice.domain.Owner;

/**
 *
 * @author patricktan
 */
@RepositoryRestResource(collectionResourceRel = "owner", path = "owner")
public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
