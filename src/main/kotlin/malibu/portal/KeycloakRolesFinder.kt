package malibu.portal

import io.micronaut.context.annotation.Primary
import io.micronaut.core.annotation.NonNull
import io.micronaut.security.token.RolesFinder
import jakarta.inject.Singleton

@Primary
@Singleton
class KeycloakRolesFinder: RolesFinder {
    override fun resolveRoles(attributes: Map<String, Any>?): @NonNull List<String> {
        val resourceRoles = attributes?.get("resource_access")?.let { clients ->
            (clients as Map<*, *>).values.flatMap { roles ->
                (roles as Map<*, *>).get("roles") as List<String>
            }
        }?: emptyList()

        val realmRoles = attributes?.get("realm_access")?.let {
            (it as Map<*, *>).get("roles") as List<String>
        }?: emptyList()

        return (resourceRoles + realmRoles).distinct()
    }
}