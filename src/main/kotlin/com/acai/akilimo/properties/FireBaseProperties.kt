package com.acai.akilimo.properties

import lombok.Data
import javax.validation.constraints.NotBlank

@Data
class FireBaseProperties {

    @NotBlank
    var configFile: String? = null

    @NotBlank
    var defaults: Map<String, String>? = null
}
