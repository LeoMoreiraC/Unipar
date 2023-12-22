<template>
  <q-page class="flex flex-center column bg">
    <q-img class="logo" src="../../../src/assets/logo.svg" />
    <q-input filled v-model="username" label="Email" class="q-mb-sm" />
    <q-input
      filled
      v-model="password"
      label="Password"
      type="password"
      class="q-mb-sm"
    />
    <q-btn
      color="dark"
      @click="entrar"
      label="Login"
      class="sign-in-buttom q-mb-sm"
    />
    <div class="flex row">
      <q-btn @click="openRegisterPage = true" class="sign-in-buttom q-mb-sm"
        >Register</q-btn
      >
    </div>
    <q-dialog v-model="openRegisterPage">
      <q-card>
        <q-card-section>
          <q-input
            filled
            v-model="email"
            :rules="emailRules"
            label="Email"
            class="q-mb-md"
          />
          <q-input
            filled
            v-model="senha"
            required
            type="password"
            label="Senha"
            class="q-mb-md"
          />
          <q-input
            filled
            v-model="confirmaSenha"
            type="password"
            required
            label="Confirma Senha"
            class="q-mb-md"
          />
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="Cancel" color="dark" v-close-popup />
          <q-btn
            flat
            label="Register"
            color="dark"
            v-if="senha == confirmaSenha"
            v-close-popup
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>
import api from "../../services/api";
export default {
  name: "signIn",
  data() {
    return {
      senha: "",
      confirmaSenha: "",
      email: "",
      password: "",
      openRegisterPage: false,
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
      ],
    };
  },
  methods: {
    async entrar() {
      api
        .post("/oauth/token", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          sessionStorage.setItem("jwt", response.data.accessToken);
          console.log(response.data.accessToken);
          this.$router.push("/main");
          //TODO: Redirect to another page
        })
        .catch((error) => {
          console.log(error);
          this.invalid = true;
          //TODO: tokern not valid
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.logo {
  width: 250px;
}
.sign-in-buttom {
  border-radius: 5px;
  width: 100px;
}
.link {
  text-decoration: none;
}
.bg {
  background-color: grey;
}
</style>
