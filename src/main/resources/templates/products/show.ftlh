<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>

<@c.page>
    <div class="parent" style="height: 260px">
        <div class="child" style="width: 30%; float: left; border: 1px solid lightgrey; height: 250px;">
            <img class="card-img-top" src="..." alt="Здесь когда-нибудь будет пикча">
        </div>
        <div class="child" style="width: 70%; float: left">
            <div class="title" style="color: blue; font-size: 30px; font-weight: bold">${product.name}</div>
            <div style="font-size: 20px; margin-top: 10px; font-weight: bold">Всего за ${product.price} рублей</div>
            <div style="margin-top: 10px">
                <form method="post">
                    <input type="submit" class="btn btn-primary" value="В корзину">
                </form>
            </div>
        </div>
    </div>

    <div class="card-text" style="margin-top: 10px">${product.description}</div>

    <div class="parent" style="margin-top: 5px">
        <div class="child" style="width: 15%; float:left;">
            <form action="/products/${product.id}/edit" method="get">
                <input type="submit" class="btn btn-primary" value="Изменить товар">
            </form>
        </div>
        <div class="child" style="width: 85%; float:left;">
            <form action="/products/${product.id}/delete" method="POST">
                <input type="submit" class="btn btn-primary" value="Удалить товар">
            </form>
        </div>
    </div>
</@c.page>