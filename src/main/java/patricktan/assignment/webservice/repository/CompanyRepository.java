package patricktan.assignment.webservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import patricktan.assignment.webservice.domain.Company;

/**
 *
 * @author patricktan
 */
@RepositoryRestResource(collectionResourceRel = "company", path = "company")
public interface CompanyRepository extends CrudRepository<Company, Long> {
}
