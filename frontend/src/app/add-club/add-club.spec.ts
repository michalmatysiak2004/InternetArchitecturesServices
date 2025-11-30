import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddClub } from './add-club';

describe('AddClub', () => {
  let component: AddClub;
  let fixture: ComponentFixture<AddClub>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddClub]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddClub);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
