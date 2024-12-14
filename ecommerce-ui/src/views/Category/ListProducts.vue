<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3"> {{category.categoryName}}</h4>
                <h5 style="font-weight: 300; color: #686868"> {{msg}} </h5>
            </div>
        </div>

        <div class="row">
            <div v-for="product of category.products" :key="product.id"
                 class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
                <ProductBox :product="product" />
            </div>
        </div>
    </div>
</template>
<script>
    import ProductBox from "../../components/ProductBox";
    export default {
        components: {ProductBox},
        data() {
            return {
                id: null,
                category: {},
                msg: ''
            }
        },
        props: ["categories"],
        mounted() {
            this.id = this.$route.params.id;

            // Verificar si el ID está definido
            if (!this.id) {
                this.msg = "ID not found in route parameters.";
                console.error('El parámetro ID no está disponible.');
                return;
            }
            console.log('Categories:', this.categories);
            // Buscar la categoría correspondiente
            this.category = this.categories.find(category => category.id == this.id);

            // Verificar si la categoría existe
            if (!this.category) {
                this.msg = "Category not found.";
                console.error('No se encontró ninguna categoría con este ID.');
                return;
            }

            // Verificar si products existe y es un arreglo
            if (!Array.isArray(this.category.products)) {
                this.msg = "No valid products data found.";
                console.error('La propiedad products no es un arreglo o no existe en la categoría.');
                return;
            }

            // Determinar el mensaje basado en la cantidad de productos
            if (this.category.products.length == 0) {
                this.msg = "No products found.";
            } else if (this.category.products.length == 1) {
                this.msg = "Only 1 product found.";
            } else {
                this.msg = `${this.category.products.length} products found.`;
            }
        }
    }
</script>
