package com.yavin.mainscreenlib.data

import com.yavin.mainscreenlib.model.CollectionWidgetType
import com.yavin.mainscreenlib.model.TranslatableField
import com.yavin.mainscreenlib.model.UiCollection
import com.yavin.mainscreenlib.model.UiWidget
import com.yavin.mainscreenlib.model.WidgetActionType
import kotlinx.coroutines.delay
import kotlin.random.Random

class MockMainUIDataRepositoryImpl : MainUIDataRepository {

    override suspend fun getCollections(): List<UiCollection> {
        delay(400)
        return getMockCollections()
    }

    override suspend fun getWidgets(ids: List<Int>): List<UiWidget> {
        delay(500)
        return getMockWidgets(ids)
    }

    override suspend fun getCoins(): Int {
        delay(200)
        return Random.nextInt(10, 1000)
    }

    private fun getMockCollections(): List<UiCollection> {
        return listOf(
            UiCollection(
                1, 1, "main_page", CollectionWidgetType.COMPACT,
                listOf(TranslatableField(1, "Продукты и для котиков"))
            ),
            UiCollection(
                2, 3, "main_page", CollectionWidgetType.BANNER,
                listOf(TranslatableField(2, "Ваш баланс"))
            ),
            UiCollection(
                9, 4, "main_page", CollectionWidgetType.FULL_WIDTH,
                listOf(TranslatableField(9, "Покупай и зарабатывай баллы"))
            ),
            UiCollection(
                7, 5, "main_page", CollectionWidgetType.FULL_WIDTH,
                listOf(TranslatableField(9, "Житие с котиком"))
            ),
            UiCollection(
                6, 7, "main_page", CollectionWidgetType.PROMO_BUTTONS,
                listOf(TranslatableField(6, "Эксперты по Котикам тут"))
            ),
            UiCollection(
                4,
                8,
                "main_page",
                CollectionWidgetType.COMPACT, // TODO turn in to COMPACT_SCROLL base on content (items.count <= 2)
                listOf(TranslatableField(4, "Замечательные котики"))
            ),
            UiCollection(
                5, 9, "main_page", CollectionWidgetType.PROMO_BUTTONS,
                listOf(
                    TranslatableField(
                        5,
                        "Эксперты по Котикам тут",
                        "badge"
                    )
                )
            )
        )
    }

    private fun getMockWidgets(widgetIds: List<Int>): List<UiWidget> {
        val widgets = getListOfMockWidgets()
        return widgets.filter { widgetIds.contains(it.collection_id) }
    }

    private fun getListOfMockWidgets(): List<UiWidget> {
        return listOf(
            UiWidget(
                8,
                null,
                1,
                1,
                WidgetActionType.DEEPLINK,
                "deeplink to another screen", // TODO
                null,
                "#d3d3d3",
                UserCatsRepositoryImpl.CAT_IMG_URL,
                "#31304a",
                "#31304a",
                CollectionWidgetType.COMPACT,
                listOf(
                    TranslatableField(8, "Рецепты для питомцев", "100 500+ рецептов", "badge")
                )
            ),
            UiWidget(
                13,
                null,
                3,
                1,
                WidgetActionType.EXTERNAL_BROWSER,
                "some cat related wiki page", // TODO
                null,
                "#d3d3d3",
                UserCatsRepositoryImpl.CAT_IMG_URL,
                "#31304a",
                "#31304a",
                CollectionWidgetType.COMPACT,
                listOf(
                    TranslatableField(8, "КотМаркет", "Заказать продукты", "Скоро \uD83E\uDD42")
                )
            ),
            UiWidget(
                1,
                UserCatsRepositoryImpl.CAT_IMG_URL,
                1,
                2,
                WidgetActionType.EXTERNAL_BROWSER,
                "some cat related wiki page", // TODO
                null,
                "#ffc107",
                "null",
                "#000000",
                "#31304a",
                CollectionWidgetType.CAT_MONEY,
                listOf(
                    TranslatableField(
                        1,
                        "КотМани",
                        "Оплачивай до 30% корма баллами \uD83D\uDCAA"
                    )
                )
            ),
            UiWidget(
                2,
                UserCatsRepositoryImpl.CAT_IMG_URL,
                2,
                2,
                WidgetActionType.EXTERNAL_BROWSER,
                "some cat related wiki page", // TODO
                null,
                "#B468FF",
                "null",
                "#000000",
                "#31304a",
                CollectionWidgetType.CAT_MONEY,
                listOf(
                    TranslatableField(
                        1,
                        "Test КотМани 2",
                        "Оплачивай до 50% влажного корма баллами \uD83D\uDCAA"
                    )
                )
            ),
            UiWidget(
                21,
                null,
                1,
                9,
                WidgetActionType.EXTERNAL_BROWSER,
                "some cat related wiki page", // TODO
                null,
                "#eeeeee",
                "SomeImageID",
                "#eeeeee",
                "#eeeeee",
                CollectionWidgetType.FULL_WIDTH,
                listOf(
                    TranslatableField(
                        id = 21,
                        subtitle = "Начисления от 1 деньги\nза 1 балл",
                        stateBadge = "badge"
                    )
                )
            ),
            UiWidget(
                22,
                null,
                2,
                9,
                WidgetActionType.DEEPLINK,
                "deeplink to another screen", // TODO
                null,
                "#f5f5f5",
                "SomeImageID",
                "#31304a",
                "#31304a",
                CollectionWidgetType.FULL_WIDTH,
                listOf(
                    TranslatableField(
                        12, "TEST Начисления от двух деньгов\n" +
                                "за 1 балл", stateBadge = "test\nbadge"
                    )
                )
            ),
            UiWidget(
                12,
                null,
                1,
                7,
                WidgetActionType.DEEPLINK,
                "deeplink to another screen", // TODO
                null,
                "#f5f5f5",
                "SomeImageID",
                "#31304a",
                "#31304a",
                CollectionWidgetType.COMPACT,
                listOf(
                    TranslatableField(12, "КотоЛайф", "Будь в форме", "test\nbadge")
                )
            ),
            UiWidget(
                10,
                "SomeImageID",
                1,
                6,
                WidgetActionType.INTERNAL_BROWSER,
                "ling to tg chanel move with rabbits", // TODO
                null,
                "#0088cc",
                null,
                "#ffffff",
                "#ffffff",
                CollectionWidgetType.PROMO_BUTTONS,
                listOf(TranslatableField(10, "Вступить в сообщество Котоводов"))
            ),
            UiWidget(
                3,
                "SomeImageID",
                2,
                6,
                WidgetActionType.INTERNAL_BROWSER,
                "link to tg chanel move with rabbits", // TODO
                null,
                "#ed174c",
                null,
                "#ffffff",
                "#ffffff",
                CollectionWidgetType.PROMO_BUTTONS,
                listOf(TranslatableField(3, "Вкусняшки за баллы"))
            ),
            UiWidget(
                7,
                "SomeImageID",
                1,
                4,
                WidgetActionType.EXTERNAL_BROWSER,
                "some cat related wiki page", // TODO
                null,
                "#f5f5f5",
                null,
                "#31304a",
                "#31304a",
                CollectionWidgetType.COMPACT_SCROLL,
                listOf(TranslatableField(7, "Котейка", "ЧешЫрскай", "badge"))
            ),
            UiWidget(
                6,
                "SomeImageID",
                2,
                4,
                WidgetActionType.EXTERNAL_BROWSER,
                "some cat related wiki page", // TODO
                null,
                "#f5f5f5",
                null,
                "#31304a",
                "#31304a",
                CollectionWidgetType.COMPACT_SCROLL,
                listOf(TranslatableField(6, "Котик", "Бегимотик", "badge"))
            ),
            UiWidget(
                5,
                "SomeImageID",
                3,
                4,
                WidgetActionType.EXTERNAL_BROWSER,
                "some cat related wiki page", // TODO
                null,
                "#f5f5f5",
                null,
                "#31304a",
                "#31304a",
                CollectionWidgetType.COMPACT_SCROLL,
                listOf(TranslatableField(5, "Кот", "Том", "badge"))
            ),
            UiWidget(
                4,
                "SomeImageID",
                1,
                5,
                WidgetActionType.EXTERNAL_BROWSER,
                "some cat related wiki page", // TODO
                null,
                "#31304a",
                null,
                "#ffffff",
                "#ffffff",
                CollectionWidgetType.PROMO_BUTTONS,
                listOf(TranslatableField(4, "Посмотреть всех"))
            ),
            UiWidget(
                11,
                "SomeImageID",
                2,
                5,
                WidgetActionType.EXTERNAL_BROWSER,
                "some cat related wiki page", // TODO
                null,
                "#ffc107",
                null,
                "#31304a",
                "#31304a",
                CollectionWidgetType.PROMO_BUTTONS,
                listOf(TranslatableField(11, "Второй котек вдогонку"))
            )
        )
    }
}