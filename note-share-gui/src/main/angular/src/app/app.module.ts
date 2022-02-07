import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule } from "@angular/common/http";

import { ReactiveFormsModule } from "@angular/forms";

import { ToastrModule } from "ngx-toastr";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoteDetailsComponent } from './components/note-details/note-details.component';
import { NoteListComponent } from './components/note-list/note-list.component';
import { NoteShareComponent } from './components/note-share/note-share.component';

@NgModule({
  declarations: [
    AppComponent,
    NoteDetailsComponent,
    NoteListComponent,
    NoteShareComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
