<template>
  <h1>terste</h1>
</template>

<script>
import { defineComponent } from "vue";

export default {
  name: "EssentialLink",
  props: {
    title: {
      type: String,
      required: true,
    },
  },
};
</script>
