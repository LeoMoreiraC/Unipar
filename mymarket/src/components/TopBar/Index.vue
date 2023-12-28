<template>
  <link
    rel="stylesheet"
    href="https://fonts.googleapis.com/icon?family=Material+Icons"
  />
  <div class="top full-width flex row justify-between absolute-top">
    {{ drawer }}
    <q-img class="icones-top items-right" src="../../assets/logo-topbar.svg" />
  </div>
</template>

<script>
import MenuDrawer from "src/components/MenuDrawer/Index.vue";
export default {
  name: "TopBar",
  data() {
    return {
      drawer: false,
    };
  },
  components: {},
};
</script>
<style lang="scss" scoped>
.top {
  background-color: $dark;
}
.icones-top {
  width: 50px;
  height: 50px;
}
.q-img {
  cursor: pointer;
}
</style>
