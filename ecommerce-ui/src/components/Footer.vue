<template>
    <footer>
        <div class="container pt-5">
            <div class="row">
                <div class="col-md-3 col-6 mx-auto text-center">
                    <ul class="footer-text" style="list-style-type: none; padding: 0; margin: 0;">
                        <li class="text-light font-weigh-bold pb-2">
                            Tienda 9Citos</li>
                        <li><a class="footer-link font-weight-light" href="https://maps.app.goo.gl/fYp1K65TsQDhV4ET8">
                            Carrera 7 # 11-09 Guateque, Boyaca
                        </a> </li>
                        <li class="footer-link font-weight-light">
                            3193656735
                        </li>
                        <li class="footer-link font-weight-light">
                            Copyright 9Citos
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
</template>

<style scoped>
.centered-list {
    display: flex;
    justify-content: center;
    align-items: center;
    list-style-type: none;
}
</style>

<style>
    footer {
        margin-top: 100px;
        background-color: #36d33b;
        font-size: 16px;
    }
     a{
         text-decoration: none;
     }
    .footer-link {
        color: #DDD;
    }
    li {
        padding-bottom: 10px;
    }

    .footer-text li {
        font-size: 1.2rem; /* Agranda el texto */
    }
    .footer-text li:first-child {
        font-size: 1.5rem; /* Título más grande */
        font-weight: bold; /* Negrita */
    }
</style>
