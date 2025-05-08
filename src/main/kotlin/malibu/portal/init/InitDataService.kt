package malibu.portal.init

import jakarta.inject.Singleton

@Singleton
class InitDataService(
    private val tagInitDataService: TagInitDataService,
    private val itemInitDataService: ItemInitDataService,
) {
    fun init() {
        val tags = tagInitDataService.init()
        itemInitDataService.init(tags)
    }
}