package com.rsschool.quiz

class DataBase {
    companion object {
        val questionList = listOf<Question>(
            Question(
                "Кто был первым князем ВКЛ",
                "Миндоуг",
                "Альгерд",
                "Витаут",
                "Казимир",
                "Ягайло",
                1
            ),
            Question(
                "какой герб у Беларусов",
                "Погоня",
                "Трезуб",
                "Белый орел",
                "Черный орел",
                "Двухглавый орел",
                1
            ),
            Question(
                "В каком году основан Минск",
                "980",
                "1105",
                "1067",
                "658",
                "1410",
                3
            ),
            Question(
                "Кто разгромил русские войска в бетве под Оршей в 1514 году",
                "Лев Сапега",
                "Жыгимонт Стары",
                "Константин Острожский",
                "Витаут",
                "Микалай Радзивил Чорный",
                3
            ),
            Question(
                "Востание Кастуся Калиновского было в ",
                "1852",
                "1905",
                "1794",
                "1837",
                "1863",
                5
            )
        )
    }
}