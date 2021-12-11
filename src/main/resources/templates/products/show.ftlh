<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>

<@c.page>
    <div class="parent" style="height: 700px">
        <div class="child" style="width: 48%; float: left; height: 250px;">
            <img src="${product.imagePath}"
                 alt="Здесь когда-нибудь будет пикча" style="border: 1px solid lightgrey;">
        </div>
        <div class="child" style="width: 52%; float: left">
            <div class="title" style="color: blue; font-size: 30px; font-weight: bold">${product.name}</div>
            <div style="font-size: 20px; margin-top: 10px; font-weight: bold">Всего за ${product.price} рублей</div>
            <div style="margin-top: 10px">
                <form method="post">
                    <input type="submit" class="btn btn-primary" value="В корзину">
                </form>
                <div class="card-text" style="margin-top: 10px">${product.description}</div>
                <div class="parent" style="margin-top: 5px">
                    <div class="child" style="width: 26%; float:left;">
                        <form action="/products/${product.id}/edit" method="get">
                            <input type="submit" class="btn btn-primary" value="Изменить товар">
                        </form>
                    </div>
                    <div class="child" style="width: 72%; float:left;">
                        <form action="/products/${product.id}/delete" method="POST">
                            <input type="submit" class="btn btn-primary" value="Удалить товар">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


</@c.page>