<template>
  <div class="title-container">
      <div class="rectangle">
        <svg-icon icon="tags" style="margin: 0 8px 0 15px;"></svg-icon>
        <span class="title-span">
          {{ title }}
        </span>
      </div>
      <div class="triangle"></div>
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
  position: absolute;
  left: -10px;

  .triangle {
    left: -20px;
    width: 0;
    height: 0;
    border-top: 10px solid #C0C4CC;
    border-right: 10px solid transparent;
    transform: scale(-1, 1);
  }

  .rectangle {
    display: flex;
    align-items: center;
    position: relative;
    line-height: 40px;
    width: 130px;
    height: 40px;
    background: #fff;
    box-shadow: 0 3px 4px rgba(0, 0, 0, .12), 0 0 12px rgba(0, 0, 0, .04);
  }

  .rectangle::before {
    content: '';
    position: absolute;
    left: 0;
    top: 0;
    width: 5px;
    height: 100%;
    background-color: #2c94fc;
  }

  .title-span {
    font-weight: 500;
    cursor: default;
  }
}
</style>
