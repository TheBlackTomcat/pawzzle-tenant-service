package pawzzle.tenant.service.domain

import pawzzle.library.service.bootstrap.Configuration
import pawzzle.library.service.domain.{EntityID, Factory, Repository}

/**
  * Trait that defines the possible operations related to data storage for the Tenant aggregate.
  */
trait TenantRepository extends Repository {

  /**
    * Adds a new Tenant by creating a new entry in the repository.
    *
    * @param tenant The tenant entity to be added.
    */
  def add(tenant: Tenant): Unit

  /**
    * Gets the tenant related data from the repository for the matching tenant id.
    *
    * @param id The id of the tenant to be retrieved.
    * @return The Tenant identified by the tenant id.
    */
  def get(id: EntityID): Option[Tenant]

  /**
    * Gets the tenant related data from the repository for the matching tenant name.
    *
    * @param name The name of the tenant to be retrieved.
    * @return The Tenant identified by the tenant name.
    */
  def get(name: TenantName): Option[Tenant]

  /**
    * Gets all the tenants found in the repository.
    *
    * @return A Set of Tenants.
    */
  def getAll(): Iterable[Tenant]
}

/**
  * Factory for instantiating a concrete TenantRepository based on the class name.
  */
object TenantRepository extends Factory {

  /**
    * Creates a new instance of TenantRepository.
    *
    * @return A new TenantRepository instance.
    */
  def apply(): TenantRepository = {
    val clazz = Configuration().getProp("tenant.storage.class")
    Class.forName(clazz).newInstance().asInstanceOf[TenantRepository]
  }
}