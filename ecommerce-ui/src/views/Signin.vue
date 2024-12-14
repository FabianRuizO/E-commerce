<template>
  <div class="conatiner">
    <div class="row">
      <div class="col-12 justify-content-center d-flex flex-row pt-5">
        <div id="signin" class="flext-item border">
          <h2 class="pt-4">Ingresar</h2>
          <form @submit="signin" class="form-group pt-4 pl-4 pr-4">
            <div class="form-group">
              <label>Correo </label>
              <input v-model="email" type="email" class="form-control" />
            </div>
            <div class="form-group">
              <label>Contraseña </label>
              <input v-model="password" type="password" class="form-control" />
            </div>
            <button class="btn btn-primary mt-2 py-0">Guardar</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import swal from "sweetalert";
export default {
  props: ["baseURL"],
  data() {
    return {
      email: null,
      password: null,
    };
  },
  methods: {
    async signin(e) {
      e.preventDefault();
      const body = {
        email: this.email,
        password: this.password,
      };
      await axios
        .post(`${this.baseURL}user/signIn`, body)
        .then((res) => {
          localStorage.setItem("token", res.data.token);
          localStorage.setItem("role", res.data.role); 
          swal({
            text: "Ingreso exitoso",
            icon: "success",
          });
          this.$emit("fetchData");
          this.$router.replace({ name: "Home" });
        })
        .catch((err) => console.log("err", err));
    },
  },
};
</script>
<style scoped>
.btn-primary {
  background-color: #ff9500;
  color: black;
}

@media screen and (min-width: 992px) {
  #signin {
    width: 40%;
  }
}
</style>
