<template>
  <div class="top flex collum justify-between absolute-left">
    <MenuDrawer :drawer="drawer" @close="drawer = false" />
    <q-icon
      name="menu"
      @click="drawer = !drawer"
      class="icones-top q-mt-md"
      :class="drawer"
    />
  </div>
</template>

<script>
import MenuDrawer from "src/components/MenuDrawer/Index.vue";
export default {
  name: "SideBar",
  data() {
    return {
      drawer: true,
    };
  },
  components: {
    MenuDrawer,
  },
};
</script>
<style lang="scss" scoped>
.top {
  background-color: $dark;
  width: 50px;
  height: 50px;
}
.icones-top {
  color: white;
}
.q-icon {
  cursor: pointer;
}
</style>
