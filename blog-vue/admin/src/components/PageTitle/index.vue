<template>
  <div class="title-container">
    <svg-icon icon="tags" class="title-icon"></svg-icon>
    <span class="title-span">
      {{ title }}
    </span>
  </div>
</template>

<script>
export default {
  name: 'PageTitle',

  props: {
    title: {
      type: String,
      require: true
    }
  }
}
</script>

<style lang="scss" scoped>
.title-container {
  display: flex;
  align-items: center;
  text-align: center;
  margin-bottom: 20px;

  .title-icon {
    font-size: 23px;
    color: #1283f3;
  }

  .title-span {
    margin-left: 10px;
    font-weight: 500;
    font-size: 17px;
    cursor: default;
  }
}
</style>
