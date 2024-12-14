<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center pt-3">
        <!-- display logo -->
      </div>
    </div>

    <!-- header -->

    <div class="row">
      <div class="col-12 justify-content-center d-flex pt-3">
        <div id="signup" class="flex-item border">
          <h2 class="pt-4 pl-4">Crear Cuenta</h2>
          <form @submit="signup" class="pt-4 pl-4 pr-4">
            <div class="form-group">
              <label for="Email">Correo</label>
              <input
                type="email"
                v-model="email"
                class="form-control"
                required
              />
            </div>
            <div class="form-row">
              <div class="col">
                <div class="form-group">
                  <label> Nombres</label>
                  <input
                    type="text"
                    v-model="firstName"
                    class="form-control"
                    required
                  />
                </div>
              </div>
              <div class="col">
                <div class="form-group">
                  <label> Apellidos </label>
                  <input
                    type="text"
                    v-model="lastName"
                    class="form-control"
                    required
                  />
                </div>
              </div>
            </div>

            <!-- password -->
            <div class="form-group">
              <label for="Password"> Contraseña</label>
              <input
                type="password"
                v-model="password"
                class="form-control"
                required
              />
            </div>

            <!-- confirm password -->
            <div class="form-group">
              <label for="Password"> Confirmar Contraseña</label>
              <input
                type="password"
                v-model="confirmPassword"
                class="form-control"
                required
              />
            </div>

            <button class="btn btn-primary mt-2">Crear Cuenta</button>
          </form>
        </div>
      </div>
    </div>

    <!-- form -->
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
      firstName: null,
      lastName: null,
      password: null,
      confirmPassword: null,
      role: null,
    };
  },
  methods: {
    async signup(e) {
      e.preventDefault();
      if (this.password === this.confirmPassword) {
        const user = {
          email: this.email,
          firstName: this.firstName,
          lastName: this.lastName,
          password: this.password,
        };
        console.log("user", user);
        await axios
          .post(`${this.baseURL}user/signup`, user)
          .then(() => {
            this.$router.replace("/");
            swal({
              text: "User signup successful, please login",
              icon: "success",
            });
          })
          .catch((err) => console.log("err", err));
      } else {
        swal({
          text: "Passwords don't match",
          icon: "error",
        });
      }
    },
    signout() {
      // L�gica de cierre de sesi�n
      localStorage.removeItem("token");
      localStorage.removeItem("role"); // Elimina el rol del almacenamiento local
      this.role = null; 
      this.$router.replace({ name: "Home" });
      // Resetea el rol en el estado local
      swal({
        text: "Logged you out. Visit again",
        icon: "success",
      });
      this.$router.replace({ name: "Home" }); // Redirige a la p�gina principal
    },
  },
  mounted() {
    // Carga el rol del almacenamiento local (si existe)
    this.role = localStorage.getItem("role");
  },
};
</script>
<style scoped>
.btn-primary {
  background-color: #ff9500;
  color: #000;
}

@media screen and (min-width: 992px) {
  #signup {
    width: 40%;
  }
}
</style>
