package pl.paweln.jhipster;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("pl.paweln.jhipster");

        noClasses()
            .that()
            .resideInAnyPackage("pl.paweln.jhipster.service..")
            .or()
            .resideInAnyPackage("pl.paweln.jhipster.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..pl.paweln.jhipster.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
