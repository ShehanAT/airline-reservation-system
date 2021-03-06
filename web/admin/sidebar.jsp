<body> 
<div id="wrapper">
    <ul class="navbar-nav bg-gradient-dark sidebar sidebar-dark accordion" id="accordionSidebar">
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="panel">
            <div class="sidebar-brand-icon rotate-n-15" style="color: #ff7f00">
                <i class="fas fa-plane"></i>
            </div>
            <div class="sidebar-brand-text mx-3">HAWKEYE</div>
        </a>
        <hr class="sidebar-divider my-0">
        <li class="nav-item">
            <a class="nav-link" href="panel">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Anasayfa</span>
            </a>
        </li>
        <hr class="sidebar-divider">
        <div class="sidebar-heading">
            HAVAYOLU FİRMASI
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#company" aria-expanded="true" aria-controls="company">
                <i class="fas fa-fw fa-cog"></i>
                <span>Firma İşlemleri</span>
            </a>
            <div id="company" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="addCompany">Firma Ekle</a>
                    <a class="collapse-item" href="companyList">Firmaları Listele</a>
                </div>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#plane" aria-expanded="true" aria-controls="plane">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Uçak İşlemleri</span>
            </a>
            <div id="plane" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="addFlight">Uçak Ekle</a>
                    <a class="collapse-item" href="ucakliste">Uçakları Listele</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider">

        <div class="sidebar-heading">
            HAVAALANI
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#Havaalanı" aria-expanded="true" aria-controls="Havaalanı">
                <i class="fas fa-fw fa-flag"></i>
                <span>Havaalanı İşlemleri</span>
            </a>
            <div id="Havaalanı" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="addAirport">Havaalanı Ekle</a>
                    <a class="collapse-item" href="airportList">Havaalanlarını Listele</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">ÜLKELER</h6>
                    <a class="collapse-item" href="countryAdd">Ülke Ekle</a>
                    <a class="collapse-item" href="country">Ülkeleri Listele</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">ŞEHİRLER</h6>
                    <a class="collapse-item" href="addCity">Add City</a>
                    <a class="collapse-item" href="listCities">List Cities</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider">

        <div class="sidebar-heading">
            UÇUŞ
        </div>
        <li class="nav-item">
            <a class="nav-link" href="createFlight">
                <i class="fas fa-fw fa-map-marked-alt"></i>
                <span>Uçuş Oluştur</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#flight" aria-expanded="true" aria-controls="ucus">
                <i class="fas fa-fw fa-map"></i>
                <span>Uçuş İşlemleri</span>
            </a>
            <div id="flight" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="currentFlightList">Güncel Uçuşları Listele</a>
                    <a class="collapse-item" href="pastFlightList">Geçmiş Uçuşları Listele</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider">
        
        <div class="sidebar-heading">
            KULLANICI
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#user" aria-expanded="true" aria-controls="user">
                <i class="fas fa-fw fa-users-cog"></i>
                <span>Kullanıcı İşlemleri</span>
            </a>
            <div id="user" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="addAdmin">Admin Ekle</a>
                    <a class="collapse-item" href="userList">Kullanıcı Listele</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider">
        
        <div class="sidebar-heading">
            REZERVASYON
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#reservation" aria-expanded="true" aria-controls="reservation">
                <i class="far fa-fw fa-calendar-alt"></i>
                <span>Reservation İşlemleri</span>
            </a>
            <div id="reservation" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="reservationList">Reservation Listele</a>
                </div>
            </div>
        </li>       
        <hr class="sidebar-divider">
        
        <div class="sidebar-heading">
            MESAJ
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#message" aria-expanded="true" aria-controls="message">
                <i class="fa-fw far fa-envelope"></i>
                <span>Message Transactions</span>
            </a>
            <div id="message" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="messageList">Gelen Mesajlar</a>
                    <a class="collapse-item" href="reviewList">Reply Listesi</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider d-none d-md-block">

        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
    </ul>
