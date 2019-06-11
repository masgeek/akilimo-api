package com.acai.akilimo.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.swagger.annotations.ApiModelProperty

import lombok.Data

import java.time.LocalDate
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Data
@JsonPropertyOrder(
        "CANBagWt",
        "CANCostperBag",
        "CANavailable",
        "CMP",
        "DAPBagWt",
        "DAPCostperBag",
        "DAPavailable",
        "FCY",
        "FR",
        "HD",
        "HD_window",
        "IC",
        "MOPBagWt",
        "MOPCostperBag",
        "MOPavailable",
        "NPK151515BagWt",
        "NPK151515CostperBag",
        "NPK151515available",
        "NPK171717BagWt",
        "NPK171717CostperBag",
        "NPK171717available",
        "NPK201010BagWt",
        "NPK201010CostperBag",
        "NPK201010available",
        "NafakaBagWt",
        "NafakaCostperBag",
        "Nafakaavailable",
        "PD",
        "PD_window",
        "PP",
        "SMS",
        "SPH",
        "SPP",
        "SSPBagWt",
        "SSPCostperBag",
        "SSPavailable",
        "TSPBagWt",
        "TSPCostperBag",
        "TSPavailable",
        "area",
        "areaUnits",
        "cassPD",
        "cassUP",
        "cassUP_m1",
        "cassUP_m2",
        "cassUP_p1",
        "cassUP_p2",
        "cassUW",
        "cost_LMO_areaBasis",
        "cost_manual_harrowing",
        "cost_manual_ploughing",
        "cost_manual_ridging",
        "cost_tractor_harrowing",
        "cost_tractor_ploughing",
        "cost_tractor_ridging",
        "cost_weeding1",
        "cost_weeding2",
        "country",
        "email",
        "fallowGreen",
        "fallowHeight",
        "fallowType",
        "harrowing",
        "intercrop",
        "lat",
        "lon",
        "maizePD",
        "maizeUP",
        "maizeUW",
        "maxInv",
        "method_harrowing",
        "method_ploughing",
        "method_ridging",
        "nameSF",
        "ploughing",
        "problemWeeds",
        "ridging",
        "riskAtt",
        "saleSF",
        "tractor_harrow",
        "tractor_plough",
        "tractor_ridger",
        "ureaBagWt",
        "ureaCostperBag",
        "ureaavailable",
        "userEmail",
        "userField",
        "userName",
        "userPhoneCC",
        "userPhoneNr")
@JsonIgnoreProperties(value = ["createdAt", "updatedAt"], allowGetters = true)
open class ComputeRequest(
        @NotBlank(message= "Country is required")
        @NotNull(message= "Country is required")
        @JsonProperty("country")
        @ApiModelProperty(example = "NG", required = true)
        var country: String
) {
    companion object {
        const val DEFAULT_UNAVAILABLE = "NA"
        const val DEFAULT_BAG_WEIGHT = "50"
    }

    /*@NotNull
    @JsonProperty("country")
    @ApiModelProperty(example = "NG", required = true)
    var country: String = null*/

    @JsonProperty("lat")
    @ApiModelProperty(example = "8.725", required = true)
    var mapLat: Double? = null

    @JsonProperty("lon")
    @ApiModelProperty(example = "4.025", required = true)
    var mapLong: Double? = null

    @JsonProperty("area")
    @ApiModelProperty(example = "1", required = true)
    var fieldArea: Double? = 1.0

    @JsonProperty("areaUnits")
    @ApiModelProperty(example = "ha", required = true)
    var areaUnits: String? = null

    @JsonProperty("intercrop")
    @ApiModelProperty(required = true, example = DEFAULT_UNAVAILABLE)
    var intercrop: String? = null

    @JsonProperty("IC")
    var interCroppingRec: Boolean = false

    @JsonProperty("FR")
    var fertilizerRec: Boolean = false

    @JsonProperty("PP")
    var plantingPracticesRec: Boolean = false

    @JsonProperty("SPP")
    var scheduledPlantingRec: Boolean = false

    @JsonProperty("SPH")
    var scheduledHarvestRec: Boolean = false

    @JsonProperty("PD")
    @ApiModelProperty(example = "2018-09-11", required = true)
    var plantingDate: String? = null

    @JsonProperty("HD")
    @ApiModelProperty(example = "2019-08-27", required = true)
    var harvestDate: String? = null

    @JsonProperty("PD_window")
    @ApiModelProperty(example = "1", required = true)
    var plantingDateWindow: Int = 0

    @JsonProperty("HD_window")
    @ApiModelProperty(example = "1", required = true)
    var harvestDateWindow: Int = 0

    @JsonProperty("fallowType")
    @ApiModelProperty(example = "bush", required = true)
    var fallowType: String? = null

    @JsonProperty("fallowHeight")
    @ApiModelProperty(example = "100", required = true)
    var fallowHeight: Int = 0

    @JsonProperty("fallowGreen")
    var fallowGreen: Boolean = false

    @JsonProperty("problemWeeds")
    var problemWeeds: Boolean = false

    @JsonProperty("tractor_plough")
    @ApiModelProperty(required = true)
    var tractorPlough: Boolean = false

    @JsonProperty("tractor_harrow")
    @ApiModelProperty(example = "areaUnit", required = true, value = "false")
    var tractorHarrow: Boolean = false

    @JsonProperty("tractor_ridger")
    @ApiModelProperty(required = true)
    var tractorRidger: Boolean = false

    @JsonProperty("cost_LMO_areaBasis")
    @ApiModelProperty(example = "areaUnit", required = true)
    var costLMOAreaBasis: String? = null

    @JsonProperty("cost_tractor_ploughing")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var costTractorPloughing: String? = null

    @JsonProperty("cost_tractor_harrowing")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var costTractorHarrowing: String? = null

    @JsonProperty("cost_tractor_ridging")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var costTractorRidging: String? = null

    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    @JsonProperty("cost_manual_ploughing")
    var costManualPloughing: String? = null

    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    @JsonProperty("cost_manual_harrowing")
    var costManualHarrowing: String? = null

    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    @JsonProperty("cost_manual_ridging")
    var costManualRidging: String? = null

    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    @JsonProperty("cost_weeding1")
    var costWeeding1: String? = null

    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    @JsonProperty("cost_weeding2")
    var costWeeding2: String? = null

    @ApiModelProperty(required = true)
    @JsonProperty("ploughing")
    var ploughing: Boolean = false

    @ApiModelProperty(required = true)
    @JsonProperty("harrowing")
    var harrowing: Boolean = false

    @ApiModelProperty(required = true)
    @JsonProperty("ridging")
    var ridging: Boolean = false

    @JsonProperty("method_ploughing")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var methodPloughing: String? = null

    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    @JsonProperty("method_harrowing")
    var methodHarrowing: String? = null

    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    @JsonProperty("method_ridging")
    var methodRidging: String? = null

    @JsonProperty("FCY")
    @ApiModelProperty(example = "11", required = true)
    var currentFieldYield: Int? = null

    @JsonProperty("CMP")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var currentMaizePerformance: String? = null

    @JsonProperty("saleSF")
    @ApiModelProperty(required = true)
    var sellToStarchFactory: Boolean = false

    @JsonProperty("nameSF")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var starchFactoryName: String? = null

    @JsonProperty("cassPD")
    @ApiModelProperty(example = "roots", required = true)
    var cassavaProduceType: String? = null

    @JsonProperty("cassUW")
    @ApiModelProperty(example = "1", required = true)
    var cassavaUnitWeight: Int = 1

    @JsonProperty("cassUP")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var cassavaUnitPrice: String? = null

    @JsonProperty("cassUP_m1")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var cassUPM1: String? = null

    @JsonProperty("cassUP_m2")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var cassUPM2: String? = null

    @JsonProperty("cassUP_p1")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var cassUPP1: String? = null

    @JsonProperty("cassUP_p2")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var cassUPP2: String? = null

    @JsonProperty("maizePD")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var maizeProduceType: String? = null

    @JsonProperty("maizeUW")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var maizeUnitWeight: String? = null

    @JsonProperty("maizeUP")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var maizeUnitPrice: String? = null

    @JsonProperty("maxInv")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var maxInvestment: String? = null

    @JsonProperty("SMS")
    var sendSms: Boolean = false

    @JsonProperty("email")
    var email: Boolean = false

    @JsonProperty("userPhoneCC")
    @ApiModelProperty(example = "254", required = true)
    @Max(4)
    @Min(3)
    var mobileCountryCode: String? = null

    @JsonProperty("userPhoneNr")
    @ApiModelProperty(example = "0711898343", required = true)
    var mobileNumber: String? = null

    @JsonProperty("fullPhoneNumber")
    @ApiModelProperty(example = "254711898343", required = true)
    var fullPhoneNumber: String? = null

    @JsonProperty("userName")
    @ApiModelProperty(example = "Akilimo", required = true)
    var userName: String? = null

    @JsonProperty("userEmail")
    @ApiModelProperty(example = "akilimo@akilimo.org", required = true)
    var userEmail: String? = null

    @JsonProperty("userField")
    @ApiModelProperty(example = "Field description", required = true)
    var fieldDescription: String? = null

    @JsonProperty("riskAtt")
    var riskAttitude: Int = 0

    @JsonProperty("ureaavailable")
    var ureaAvailable: Boolean = false
    @JsonProperty("ureaCostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var ureaCostPerBag: String? = null
    @JsonProperty("ureaBagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var ureaBagWeight: Int = 0

    @JsonProperty("MOPavailable")
    var mopAvailable: Boolean = false
    @JsonProperty("MOPCostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var mopCostperBag: String? = null
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    @JsonProperty("MOPBagWt")
    var mopBagWeight: Int = 0

    @JsonProperty("DAPavailable")
    var dapAvailable: Boolean = false
    @JsonProperty("DAPCostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var dapCostPerBag: String? = null
    @JsonProperty("DAPBagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var dapBagWeight: Int = 0

    @JsonProperty("NPK201010available")
    var npkTwentyAvailable: Boolean = false
    @JsonProperty("NPK201010CostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var npkTwentyCostPerBag: String? = null
    @JsonProperty("NPK201010BagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var npkTwentyBagWeight: Int = 0

    @JsonProperty("NPK151515available")
    var npkFifteenAvailable: Boolean = false
    @JsonProperty("NPK151515CostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var npkFifteenCostPerBag: String? = null
    @JsonProperty("NPK151515BagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var npkFifteenBagWeight: Int = 0

    @JsonProperty("TSPavailable")
    var tspAvailable: Boolean = false
    @JsonProperty("TSPCostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var tspCostPerBag: String? = null
    @JsonProperty("TSPBagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var tspBagWeight: Int = 0

    @JsonProperty("NPK171717available")
    var npkSeventeenAvailable: Boolean = false
    @JsonProperty("NPK171717CostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var npkSeventeenCostPerBag: String? = null
    @JsonProperty("NPK171717BagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var npkSeventeenBagWeight: Int = 0

    @JsonProperty("Nafakaavailable")
    var nafakaAvailable: Boolean = false
    @JsonProperty("NafakaCostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var nafakaCostPerBag: String? = null
    @JsonProperty("NafakaBagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var nafakaBagWeight: Int = 0

    @JsonProperty("CANavailable")
    var canAvailable: Boolean = false
    @JsonProperty("CANCostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var canCostPerBag: String? = null
    @JsonProperty("CANBagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var canBagWeight: Int = 0

    @JsonProperty("SSPavailable")
    var sspAvailable: Boolean = false
    @JsonProperty("SSPCostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var sspCostPerBag: String? = null
    @JsonProperty("SSPBagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var sspBagWeight: Int = 0

    /*
    @JsonProperty("newFert1name")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert1name: String? = null
    @JsonProperty("newFert1N_cont")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert1NCont: String? = null
    @JsonProperty("newFert1P2O5")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert1P2O5: String? = null
    @JsonProperty("newFertK2O")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFertK2O: String? = null
    @JsonProperty("newFertCostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFertCostPerBag: String? = null
    @JsonProperty("newFert1BagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var newFert1BagWeight: Int = 0


    @JsonProperty("newFert2name")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert2name: String? = null
    @JsonProperty("newFert2N_cont")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert2NCont: String? = null
    @JsonProperty("newFert2P2O5")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert2P2O5: String? = null
    @JsonProperty("newFert2K2O")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert2K2O: String? = null
    @JsonProperty("newFert2CostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert2CostPerBag: String? = null
    @JsonProperty("newFert2BagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var newFert2BagWeight: Int = 0


    @JsonProperty("newFert3name")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert3name: String? = null
    @JsonProperty("newFert3N_cont")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert3NCont: String? = null
    @JsonProperty("newFert3P2O5")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert3P2O5: String? = null
    @JsonProperty("newFert3K2O")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert3K2O: String? = null
    @JsonProperty("newFert3CostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert3CostPerBag: String? = null
    @JsonProperty("newFert3BagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var newFert3BagWeight: Int = 0


    @JsonProperty("newFert4name")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert4name: String? = null
    @JsonProperty("newFert4N_cont")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert4NCont: String? = null
    @JsonProperty("newFert4P2O5")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert4P2O5: String? = null
    @JsonProperty("newFert4K2O")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert4K2O: String? = null
    @JsonProperty("newFert4CostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert4CostPerBag: String? = null
    @JsonProperty("newFert4BagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var newFert4BagWeight: Int = 0


    @JsonProperty("newFert5name")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert5name: String? = null
    @JsonProperty("newFert5N_cont")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert5NCont: String? = null
    @JsonProperty("newFert5P2O5")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert5P2O5: String? = null
    @JsonProperty("newFert5K2O")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert5K2O: String? = null
    @JsonProperty("newFert5CostperBag")
    @ApiModelProperty(example = DEFAULT_UNAVAILABLE, required = true)
    var newFert5CostPerBag: String? = null
    @JsonProperty("newFert5BagWt")
    @ApiModelProperty(example = DEFAULT_BAG_WEIGHT, required = true)
    var newFert5BagWeight: Int = 0

     */
}