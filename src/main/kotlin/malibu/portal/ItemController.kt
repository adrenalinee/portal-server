package malibu.portal

//@Singleton
//open class ItemService(
//    private val itemRepo: ItemRepo
//) {
//    @Transactional
//    open fun create(
//        @Valid @Body createSpec: ItemCreateSpec
//    ): Item {
//        return itemRepo.save(Item.create(
//            organizationId = "1234",
//            createSpec = createSpec
//        ))
//    }
//
//    @Transactional(readOnly = true)
//    open fun findAll(
//        searchSpec: ItemSearchSpec?,
//        pageable: Pageable
//    ): Page<Item> {
//        return itemRepo.findAll(pageable)
//            .also { page ->
//                page.content.forEach { item ->
//                    println(item.children)
//                    println(item.tags)
//                }
//            }
//    }
//
//    @Transactional
//    open fun patch(
//        id: UUID,
//        updateSpec: ItemUpdateSpec
//    ): Item {
//        val item = itemRepo.findById(id)
//            .orElseThrow { RuntimeException("item not found. id: $id") }
//
//        item.patch(updateSpec)
//
//        return item
//    }
//}