<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h3 class="pt-3">Añadir Categoria</h3>
      </div>
    </div>
    <div class="row">
      <div class="col-3"></div>
      <div class="col-6">
        <form>
          <div class="form-group">
            <label>Nombre</label>
            <input type="text" class="form-control" v-model="categoryName" />
          </div>
          <div class="form-group">
            <label>Descripción</label>
            <textarea type="text" class="form-control" v-model="description" />
          </div>
          <div class="form-group">
            <label>Imagen</label>
            <input type="text" class="form-control" v-model="imageUrl" />
          </div>
          <button type="button" class="btn btn-primary" @click="addCategory">
            Guardar
          </button>
        </form>
      </div>
      <div class="col-3"></div>
    </div>
  </div>
</template>
<script>
const axios = require("axios");
const sweetalert = require("sweetalert");
export default {
  data() {
    return {
      categoryName: "",
      description: "",
      imageUrl: "",
    };
  },
  methods: {
    addCategory() {
      console.log(this.categoryName, this.description);
      const newCategory = {
        categoryName: this.categoryName,
        description: this.description,
        imageUrl: this.imageUrl,
      };

      const baseURL = "http://localhost:8080/";

      axios({
        method: "post",
        url: `${baseURL}/category/create`,
        data: JSON.stringify(newCategory),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then(() => {
          this.$emit("fetchData");
          this.$router.push({name:'AdminCategory'});
          sweetalert({
            text: "Category added successfully",
            icon: "success",
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style scoped></style>
