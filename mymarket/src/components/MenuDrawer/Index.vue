<template>
  <div class="q-pa-md">
    <q-drawer
      show-if-above
      :width="200"
      :breakpoint="700"
      elevated
      v-model="open"
      @hide="hide"
      class="bg-dark text-white"
    >
      <q-scroll-area class="fit">
        <div class="q-pa-sm"></div>
        <div class="q-pa-sm"></div>
        <div @click="irConfig" class="menus q-pa-sm">
          <q-icon name="settings"></q-icon>
          <span> Configurações</span>
        </div>
        <div @click="irInventario" class="menus q-pa-sm">
          <q-icon name="inventory"></q-icon>
          <span> Inventário</span>
        </div>
        <div @click="irChamados" class="menus q-pa-sm">
          <q-icon name="call"></q-icon>
          <span> Chamados</span>
        </div>
        <div @click="irUsuarios" class="menus q-pa-sm">
          <q-icon name="person"></q-icon>
          <span> Usuários</span>
        </div>
      </q-scroll-area>
    </q-drawer>
  </div>
</template>

<script>
import { defineComponent } from "vue";

export default {
  name: "MenuDrawer",
  data() {
    return {
      open: false,
    };
  },
  props: {
    drawer: {
      type: Boolean,
      default: false,
      required: true,
    },
  },
  watch: {
    drawer() {
      this.open = this.drawer;
    },
  },
  methods: {
    irInventario() {
      this.$router.push("/inventario");
    },
    irConfig() {
      this.$router.push("/configuracoes");
    },
    irChamados() {
      this.$router.push("/chamados");
    },
    irUsuarios() {
      this.$router.push("/usuarios");
    },
    hide() {
      this.$emit("close");
    },
  },
};
</script>

<style lang="scss" scoped>
.icones-brancos {
  color: white;
}
.bg-branco {
  background-color: white;
}
.menus {
  cursor: pointer;
}
</style>
