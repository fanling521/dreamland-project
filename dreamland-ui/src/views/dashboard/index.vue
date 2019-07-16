<template>
  <div class="dashboard-container">
    <div class="dashboard-text">欢迎: {{ name }}</div>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: 'Dashboard',
    data() {
      return {}
    },
    computed: {
      ...mapGetters([
        'name'
      ])
    }
  }
</script>
