import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { Patients } from './patients/patients';
import { ListePatients } from './liste-patients/liste-patients';

import { Home } from './home/home';
import { NewCustomer } from './new-customer/new-customer';
import { Heartreports } from './heartreports/heartreports';
import { Healthplans } from './healthplans/healthplans';

export const routes: Routes = [ 

    {
        path:'patients',
        component:Patients
    },
    {
        path:'liste-patients',
        component:ListePatients
    },
     {//default page
         path: '', component:Home
         },
         {
            path:'new-customer',
            component:NewCustomer
         },
         {
         path:'heartreports',
            component:Heartreports
         },
         {
            path:'health',
            component:Healthplans,
         }

    
];
