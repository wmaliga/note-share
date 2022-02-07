import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-note-share',
  templateUrl: './note-share.component.html',
  styleUrls: ['./note-share.component.css']
})
export class NoteShareComponent implements OnInit {

  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder
  ) {
    this.form = this.newForm();
  }

  ngOnInit(): void {
  }

  shareNote() {
    console.log(this.form.value);
    this.form.reset();
  }

  clearForm() {
    this.form.reset();
  }

  private newForm() {
    return this.formBuilder.group({
      title: ['', Validators.required],
      data: ['', Validators.required]
    });
  }
}
