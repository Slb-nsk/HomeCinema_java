<!doctype html>
<html>
    <head>
    </head>
    <body>

    <h1 align=center>Список фильмов в базе</h1>

       <#if searchResult?size gt 0>
            <div>
                <ul>
                    <#list searchResult as item>
                        <li>"${item.movieRussianName}", количество серий:${item.seriesAmount}</li>
                    </#list>
                </ul>
            </div>
        </#if>


    </body>
</html>
