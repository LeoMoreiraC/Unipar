<template>
  <q-page class="flex flex-center">
    <div>
      <TopBar />
    </div>
    <div>
      <SideBar />
    </div>
  </q-page>
</template>

<script>
import TopBar from "src/components/TopBar/Index.vue";
import SideBar from "src/components/SideBar/Index.vue";
export default {
  name: "MainPage",
  components: {
    TopBar,
    SideBar,
  },
};
</script>
