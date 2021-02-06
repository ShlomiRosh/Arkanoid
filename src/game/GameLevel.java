/* name: shalomi rosh
   id: 308154418
   oop
*/
package game;

import biuoop.DrawSurface;
import geometry.Rectangle;
import geometry.Point;
import java.awt.Color;
import java.util.List;
import biuoop.KeyboardSensor;
import geometry.Velocity;
import assix.KeyPressStoppableAnimation;
import sprite.*;

/**
 * Game class creates an entire game environment, including.
 * all necessary elements.
 */
public class GameLevel implements Animation {

    private int liveCount;
    private BlockRemover remover;
    private BallRemover ballRemover;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scors;
    private Counter live;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    /**
     * The constructor for the Game.
     * @param level the Level information needed.
     * @param ar the Animation runner.
     * @param key the Keyboard for the user.
     * @param livesCounter the number of lives.
     * @param scoreCounter the Score value.
     */
    public GameLevel(LevelInformation level, AnimationRunner ar, KeyboardSensor key,
                     Counter livesCounter, Counter scoreCounter) {
        this.level = level;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.remover = new BlockRemover(this, blockCounter);
        this.ballCounter = new Counter();
        this.ballRemover = new BallRemover(this, ballCounter);
        this.scors = scoreCounter;
        this.live = livesCounter;
        this.runner = ar;
        this.keyboard = key;
        this.liveCount = live.getValue();
    }
    /**
     * Gets the remaining block count.
     * @return the amount of remaining blocks.
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }
    /**
     * Gets the remaining lives count.
     * @return the amount of remaining blocks.
     */
    public Counter getLive() {
        return live;
    }

    /**
     * add collidables.Collidable method adds a given.
     * collidable object to the game environment.
     * @param c - a sent collidable.
     */
    public void addCollidable(Callable c) {
        environment.addCollidable(c);
    }
    /**
     * addSprite adds a sprite to the list of sprites.
     * belonging to the Game.
     * @param s a sent sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * this method initializes a new Game.
     * it creates all necessary elements, such as keyBoard sensor.
     * gui, and the game balls.
     */
    public void initialize() {
        addSprite(this.level.getBackground());
        border();
        block();

        LevelIndicator levelIndicator = new LevelIndicator(this.level.levelName());
        levelIndicator.addToGame(this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scors);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.live);
        livesIndicator.addToGame(this);
    }
    /**
     * This method creates the boundaries of the screen.
     */
    public void border() {

        /**This will create the borders of the screen **/
        SpecialBlocks u = new SpecialBlocks(new Rectangle(new Point(0, 20), 800, 20), Color.GRAY, 1
                , true, false, false, false);
        SpecialBlocks r = new SpecialBlocks(new Rectangle(new Point(0, 0), 20, 600), Color.GRAY, 1
                , true, false, false, false);
        SpecialBlocks l = new SpecialBlocks(new Rectangle(new Point(780, 0), 20, 600), Color.GRAY, 1
                , true, false, false, false);
        SpecialBlocks d = new SpecialBlocks(new Rectangle(new Point(20, 599), 758, 8),Color.GRAY
                , 1, true, false, false, false);
        SpecialBlocks uu = new SpecialBlocks(new Rectangle(new Point(0, 0), 800, 20),
                new Color(0x484745), 1, true, false, false, false);
        /**This will add all the border blocks to the game.**/
        u.addToGame(this);
        r.addToGame(this);
        l.addToGame(this);
        d.addToGame(this);
        uu.addToGame(this);
        /**Add the bottom black as the death-region.**/
        d.addHitListener(ballRemover);
    }

    /**
     * This method create the paddle.
     * @return paddle
     */
    public Paddle paddle() {
        int pointX = this.runner.getGui().getDrawSurface().getWidth() / 2
                - this.level.paddleWidth() / 2;
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(pointX, 570),
                this.level.paddleWidth(), 20), this.level.paddleSpeed(), this.level.paddleWidth());
        paddle.addToGame(this);
        return paddle;
    }
    /**
     * This method create the balls on the top of the paddle.
     */
    public void ball() {
        int pointX = this.runner.getGui().getDrawSurface().getWidth() / 2
                - this.level.paddleWidth() / 2;
        // Creates multiple bouncing balls.
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(pointX + level.paddleWidth() / 2, 564),
                    7, Color.RED, true, true, false);
            List<Velocity> vList = this.level.initialBallVelocities();
            //Velocity v = Velocity.fromAngleAndSpeed(vList.get(i).getDx(), vList.get(i).getDy());
            ball.setVelocity(vList.get(i));

            ball.addToGame(this);
            this.ballCounter.increase(1);
            ball.setGameEnvironment(environment);
        }
    }
    /**
     * This method create the blocks for the level.
     */
    public void block() {
        for (int i = 0; i < this.level.blocks().size(); i++) {
            Block block = new Block(this.level.blocks().get(i));
            block.addHitListener(new ScoreTrackingListener(this.scors));
            block.addToGame(this);
            this.blockCounter.increase(1);
            block.addHitListener(remover);
        }
    }
    /**
     * Method that will remove the Collidable.
     * @param c the Collidable to be removed.
     */
    public void removeCollidable(Callable c) {
        this.environment.removeColliadable(c);
    }
    /**
     * Method to remove a Sprite.
     * @param s the Sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * Method that will run at the start of each turn.
     */
    public void playOneTurn() {
        Paddle p = paddle();
        ball();
        this.runner.run(new CountdownAnimation(3, 3, sprites));
        this.running = true;
        this.runner.setFramesPerSecond(650);
        this.runner.run(this);
        if (this.ballCounter.getValue() == 0) {
            live.decrease(1);
        }
        p.removeFromGame(this);
    }
    /**
     * Boolean that will check when the game must stop.
     * @return true or false.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Method that will draw the surface at each frame.
     * @param d object that needs to be drawn.
     * @param dt the frames per second.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        if ((this.blockCounter.getValue() == 0) || (this.ballCounter.getValue() == 0)) {
            if (this.blockCounter.getValue() == 0) {
                this.scors.increase(100);
            }
            this.running = false;
        }
    }
}