<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/header.jsp"/>
<section class="padding-large bg-light">
    <div id="carouselExampleControls" class="carousel slide main-slider" data-ride="carousel">
        <div class="carousel-inner container">
            <div class="carousel-item active">
                <div class="container w-75 d-flex">
                    <div class="carousel-caption d-block">
                        <h1>You will see top games in the world</h1>
                        <h3> all games on all available platforms develop by amazing publishers</h3>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="container w-75 d-flex">
                    <div class="carousel-caption d-block">
                        <h1>Get knowledge about upcoming premieres</h1>
                        <h3> news and articles about publishers, developers</h3>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="container w-75 d-flex">
                    <div class="carousel-caption d-block">
                        <h1>search any games in our amazing games database</h1>
                        <h3> You will find any game at any category or year release</h3>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-pev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</section>
<section class="section-more padding-small">
    <div class="container d-flex justify-content-between">
        <div class="mr-4">
            <h1 class="pb-3">Sign in</h1>
            <h4 class="pt-1">sign in to rate and comment video games</h4>
        </div>
        <div class="ml-4 align-self-center">
            <button class="btn btn-color rounded-0 mt-4 pl-4 pr-4">
                <a href="https://en.wikipedia.org/wiki/Lists_of_video_games">list on wipipedia</a>
            </button>
        </div>
    </div>
</section>
<section class="padding-small details bg-light">
    <div class="container">
        <div class="row">
            <div class="col text-center">
                <i class="fas fa-check icon-details"></i>
                <h1>rate</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
                </p>
            </div>
            <div class="col text-center pr-4 pl-4 mr-4 ml-4">
                <i class="far fa-clock icon-details"></i>
                <h1>comment</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
                </p>
            </div>
            <div class="col text-center">
                <i class="fas fa-list icon-details"></i>
                <h1>discuss</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
                </p>
            </div>
        </div>
    </div>
</section>
<section class="newsletter-section padding-small">
    <div class="container">
        <div class="row">
            <div class="col">
                <h1>Learn more about games</h1>
            </div>
            <div class="col-5">
                <div class="input-group mb-3">
                    <input type="text" class="form-control border-0 rounded-0" placeholder=""
                           aria-label="Recipient's username" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="input-group-text btn-color border-0 rounded-0" type="submit" id="basic-addon2">
                            <a href="https://en.wikipedia.org/wiki/Video_game">LEARN</a>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="padding-medium story bg-light" id="about">
    <div class="container d-flex justify-content-center align-items-center">
        <div class="row">
            <div class="col-4 mr-4">
                <div class="div-img">
                </div>
            </div>

            <div class="col-7 ml-4">
                <h1 class="pb-1">About games</h1>
                <p>The history of video games began in the 1950s and 1960s as computer scientists began designing simple
                    games and simulations on minicomputers and mainframes. Spacewar! was developed by MIT student hobbyists
                    in 1962 as one of the first such games on a video display. The first consumer video game hardware was
                    released in the early 1970s. The first home video game console is the Magnavox Odyssey, and the first
                    arcade video games are Computer Space and Pong. After its home console conversions, numerous companies
                    sprang up to capture Pong's success in both the arcade and the home by cloning the game, causing series
                    of boom and bust cycles due to oversaturation and lack of innovation.
                </p>
            </div>
        </div>
    </div>
</section>
<jsp:include page="parts/footer.jsp"/>