//board
var BlockSize = 20;
var rows = 15;
var cols = 15;
var board;
var context;

//head of butete
var snakeX = BlockSize * 4;
var snakeY = BlockSize * 4;

//food (tae)
var foodX;
var foodY;
var score = 0;
//speed
var velocityX = 0;
var velocityY = 0;

var gameOver = false;


//body
var snakeBody = [];
window.onload = function(){
  board = document.getElementById("board");
  board.height = rows * BlockSize;
  board.width = cols * BlockSize;
  context = board.getContext("2d");//drawing on board
  placeTae();
  document.addEventListener("keyup", changeDirection);
 // update();
 setInterval(update, 100);
//resetVar();
}

function resetVar(){
  gameOver = false;
  score = 0;
  snakeX =  BlockSize * 4;
  snakeY =  BlockSize * 4;
  velocityX = 0;
  velocityY = 0;
  snakeBody = [];
  placeTae();
}

function update(){
  if(gameOver){
    resetVar();
    //update();
    return;
  }
  
  //board
  context.fillStyle="black";
  context.fillRect(0,0,board.width,board.height);
  
  //food
  context.fillStyle = "#964B00";
  context.fillRect(foodX, foodY, BlockSize, BlockSize);
  
  if (snakeX == foodX && snakeY == foodY){
    snakeBody.push([foodX, foodY]);
    score += 1;
    placeTae();
  }
 context.font = "20px Arial";
 context.fillText(score, 10, 20);
  for(let i = snakeBody.length-1; i> 0;i--){
    snakeBody[i]= snakeBody[i-1];
  }
  if(snakeBody.length){
    snakeBody[0] = [snakeX, snakeY];
  }
   //butete head
  context.fillStyle="yellow";
  snakeX += velocityX * BlockSize;
  snakeY += velocityY * BlockSize;
  context.fillRect(snakeX, snakeY, BlockSize, BlockSize);
  for (let i =0; i < snakeBody.length;i++) {
    context.fillRect(snakeBody[i][0],snakeBody[i][1], BlockSize, BlockSize);
  }
  
  //is GameOver
  if(snakeX < 0 || snakeX> cols*BlockSize || snakeY < 0 || snakeY > rows * BlockSize){
    gameOver = true;
    alert("Idiot!! why did you hit the wall!?");
    //resetVar();
  }
  
  for (let i = 0; i < snakeBody.length; i++) {
    if(snakeX == snakeBody[i][0] && snakeY == snakeBody[i][1]){
      gameOver = true;
      alert("Greedy bitch!!");
      
    }
  }
}

function placeTae(){
  foodX = Math.floor(Math.random()*cols) * BlockSize;
  foodY = Math.floor(Math.random()*rows) * BlockSize;
  
}

function changeDirection(e){
     if (e == "ArrowUp") {
       action('up');
     }
    else if (e == "ArrowDown") {
       action('down');
     }
    else if (e == "ArrowLeft") {
       action('left');
     }
    else if (e == "ArrowRight") {
       action('right');
     }
}

function action(f){
   if (f == "up" && velocityY != 1){
    velocityX =0;
    velocityY = -1;
  }
 else if (f == "down" && velocityY != -1) {
     velocityX = 0;
     velocityY = 1;
   }
 else if (f == "left" && velocityX != 1) {
     velocityX = -1;
     velocityY = 0;
   }
 else if (f == "right" && velocityX != -1) {
      velocityX = 1;
      velocityY = 0;
    }
}
