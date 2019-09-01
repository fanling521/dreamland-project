<template>
  <div class="dashboard-container">
    <panel-group/>
  </div>
</template>

<script>
    import PanelGroup from './components/PanelGroup'

    export default {
        name: 'Dashboard',
        components: {
            PanelGroup
        },
        data() {
            return {}
        }
    }
</script>
<style lang="scss" scoped>
  .dashboard-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    position: relative;
  }

  .dashboard-bg {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
</style>
