package com.ccspart2.projectcerberusadmincompose.data.model

data class Location(
    val id: String = "",
    val name: String = "",
    val entrances: Int = 0,
    val positions: Int = 0,
    val suggestedCount: Int = 0
) {
    companion object {
        fun mock(): Location {
            return Location(
                id = "0",
                name = "Coliseo de Puerto Rico",
                entrances = 1,
                positions = 1,
                suggestedCount = 1)
        }
    }
}
