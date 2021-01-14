<template>
  <div class="window border border-secondary rounded p-2 mb-2" :class="{expanded: isExpanded}">
    <div class="top-row d-flex" @click="toggleExpand">
      <div class="window-name fw-bold pe-3">{{window.name}}</div>
      <div class="room-name text-muted">{{window.roomName}}</div>

      <div class="open-status ms-4" :class="{open: isWindowOpen, closed: !isWindowOpen}">
        <template v-if="isWindowOpen">
          <span class="icon">&#x2B24;</span> Open
        </template>
        <template v-else>
          <span class="icon">&#x2716;</span> Closed
        </template>
      </div>

      <div class="expand-button ms-auto">
        {{ isExpanded ? '&#9660;' : '&#9658;' }}
      </div>
    </div>
    <template v-if="isExpanded">
      <hr/>
      <div class="details d-flex">
        <button type="button" class="btn btn-secondary me-2" @click="switchWindow">{{ isWindowOpen ? 'Close' : 'Open' }} window</button>
        <button type="button" class="btn btn-secondary me-2" @click="deletWindow(window.id)">Delete window</button>
        
        <button type="button" class="btn btn-danger me-2" @click="createNew">Create window</button>
      </div>
    </template>
    
    <template v-if="okCreate">
          <br><form @submit="postMethod" method="POST">
            <div class="row">
              <div class="col">
                <input type="text" class="form-control" placeholder="Windos name" v-model="postwindow.name">
              </div>
              <div class="col">
                <input type="text" class="form-control" placeholder="Windows status"   v-model="postwindow.windowStatus">
              </div>
              <div class="col">
                <input type="text" class="form-control" placeholder="Room name"  v-model="postwindow.roomName">
              </div>
              <div class="col">
                <input type="text" class="form-control" placeholder="Room ID"  v-model="postwindow.roomId">
              </div>
              <div class="col">
                <input type="submit" class="form-control btn btn-success me-6" >
              </div>
            </div>
          </form>
          <br><h1>hi</h1>
        </template>
        <template v-else>
          <p hidden>no</p>
        </template>
  
    
  </div>
</template>

<script>
import axios from 'axios';
import vue from 'vue';

import {API_HOST} from '../config';

export default {
  name: 'WindowsListItem',
  props: ['window'],
  data: function() {
    return {
      isExpanded: false,
      okCreate: false,
      postwindow:{
        
        name: null,
        windowStatus: null,
        roomName:null,
        roomId: null
        
      }
    }
  }, 
  computed: {
    isWindowOpen: function() {
      return this.window.windowStatus === 'OPEN'; 
    }
  },
  methods: {
    toggleExpand() {
      this.isExpanded = !this.isExpanded;
    },
    async switchWindow() {
      let response = await axios.put(`http://localhost:8099/api/windows/${this.window.id}/switch`);
      let updatedWindow = response.data;
      this.$emit('window-updated', updatedWindow);
    },
    async deletWindow(id){
      await axios.delete('http://localhost:8099/api/windows/'+id);
    },
    
    async createNew(){
     this.okCreate = this.okCreate ? false: true;
    },
     async postMethod(e){
      //  console.log(this.postwindow);
       e.preventDefault();
       await axios.post('http://localhost:8099/api/windows', this.postwindow)
        .then(result =>{
          console.warn(result)
        })
     }
    // ,
    // async creatWindow(window){
    //   axios.post('http://localhost:8090/api/windows/')
    // }
  }
}
</script>

<style lang="scss" scoped>

.open-status {
  .icon {
    position: relative;
  }

  &.open {
    color: #198754;
    .icon {
      font-size: 12px;
      top: -3px;
    }
  }

  &.closed {
    color: #dc3545;
  }
}

.window {
  .top-row {
    cursor: pointer;
  }
}
</style>
