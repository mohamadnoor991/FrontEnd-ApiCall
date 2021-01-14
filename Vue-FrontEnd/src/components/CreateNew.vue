<template>
<div>
<div>
   
  <template >
 
      <div class="details d-flex flex-column ">  
        <button type="button" class="btn btn-danger me-2 m-3 p-3" @click="createNew">Create New Window</button>
            <template v-if="okCreate">
          <br><form @submit="postMethod" method="POST" class="border border-success p-4">
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-5 col-form-label">Windos name</label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="Windos name" v-model="postwindow.name">
              </div>
            </div><br>
              
              <div class="form-group row">
                <label for="inputEmail3" class="col-sm-5 col-form-label">status</label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="OPEN or CLOSED"   v-model="postwindow.windowStatus">
              </div>
              </div><br>
              
              <div class="form-group row">
                <label for="inputEmail3" class="col-sm-5 col-form-label">Room name</label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="ex: room 1"  v-model="postwindow.roomName">
              </div>
              </div><br>
              
              <div class="form-group row">
                <label for="inputEmail3" class="col-sm-5 col-form-label">Room ID</label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="ex: 2"  v-model="postwindow.roomId">
              </div>
              </div><br>
              
              <div class="form-group row">
              <div class="col-sm-10">
                <input type="submit" class="form-control btn btn-success me-6" >
              </div>
              </div>
           
          </form>
          <br><h1>hi</h1>
        </template>
        <template v-else>
          <p hidden>no</p>
        </template>
        
        <button type="button" class="btn btn-danger me-2 m-3 p-3" @click="createRoom">Create New Room</button>
      </div>
    </template>
    

        
            <template v-if="okCreateRoom">
          <br><form @submit="postMethodRoom" method="POST" class="border border-success p-4">
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-5 col-form-label">Room name</label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="ex: room 10" v-model="postRoom.name">
              </div>
            </div><br>
              
              <div class="form-group row">
                <label for="inputEmail3" class="col-sm-5 col-form-label">Floor Number</label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="ex: 14"   v-model="postRoom.level">
              </div>
              </div><br>
              
              <div class="form-group row">
                <label for="inputEmail3" class="col-sm-5 col-form-label">Room Temperature ℃ </label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="ex: 20.00 ℃ "  v-model="postRoom.currentTemperature">
              </div>
              </div><br>
              
              <div class="form-group row">
                <label for="inputEmail3" class="col-sm-5 col-form-label">Target Temperature ℃ </label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="ex: 15.26 ℃"  v-model="postRoom.targetTemperature">
              </div>
              </div><br>
              
              <div class="form-group row">
              <div class="col-sm-10">
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
    </div>
</template>



<script>
import axios from 'axios';
import vue from 'vue';

import {API_HOST} from '../config';

export default {
  name: 'CreateNew',
//   props: ['window'],
  data: function() {
    return {
      isExpanded: false,
      okCreate: false,
      okCreateRoom: false,
      
      postwindow:{
        name: null,
        windowStatus: null,
        roomName:null,
        roomId: null
      },
      postRoom:{
          level:null,
          name:null,
          currentTemperature:null,
          targetTemperature:null,
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

    async deletWindow(id){
      await axios.delete('http://localhost:8099/api/windows/'+id);
    },
    async createNew(){
     this.okCreate = this.okCreate ? false: true;
    },
    async createRoom(){
     this.okCreateRoom = this.okCreateRoom ? false :true;
    }
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
